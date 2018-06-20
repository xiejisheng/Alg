# coding:utf-8
import time
import datetime
import pytz
import hmac
import hashlib
import httplib
import json
import logging
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s:%(message)s",
    datefmt='[%Y-%m-%d %H:%M:%S]'
)

appsecret = "96e69d87d91db4d9347567339abf40d3"
appkey = "waimai_sys"


def ba_headers(method, uri):
    date_str = datetime.datetime.now(pytz.timezone("GMT")).strftime("%a, %-d %b %Y %H:%M:%S %Z")
    data = "%s %s\n%s" % (method,uri,date_str)
    sign = hmac.new(appsecret, data, hashlib.sha1).digest().encode('base64').rstrip()
    authentication = "%s %s:%s" % ("MWS", appkey, sign)
    return {"Date":date_str, "Authorization":authentication}


def get(host, port=80, uri=None, headers={}):
    conn = httplib.HTTPConnection(host, port)
    conn.request('GET', uri, '', headers)
    response = conn.getresponse().read()
    obj = json.loads(response)
    return obj


def post(host, port=80, uri=None, body={}, headers={}):
    conn = httplib.HTTPConnection(host, port)
    conn.request('POST', uri, json.dumps(body), headers)
    response = conn.getresponse().read()
    obj = json.loads(response)
    return obj


class ETL(object):

    def __init__(self, name, next, args="-v --delta 1"):
        self.name = name
        self.args = "-v --delta 1"
        self.next = next
        self.status = 0
        self.exec_id = 0

tasks = [
ETL("hmart_waimai_crm.mix_dt__all_org_aortype_audit", ETL("mart_waimai_app.app_dt__all_org_aortype_audit", None)),
ETL("hmart_waimai_crm.mix_dt_all_org__org_audit", ETL("mart_waimai_app.topic_dt_all_org__org_audit", None)),
ETL("hmart_waimai_crm.mix_mo__all_org_aortype_audit", ETL("mart_waimai_app.app_mo__all_org_aortype_audit", None)),
ETL("hmart_waimai_crm.mix_mo_all_org__org_audit", ETL("mart_waimai_app.topic_mo_all_org__org_audit", None)),
ETL("hmart_waimai_crm.mix_dt_user__process_data", ETL("mart_waimai_app.topic_dt_user__tar_and_com", None)),
ETL("mart_waimai_app.topic_mo_user__swordge_data", None),
# ETL("hmart_waimai_crm.mix_mo_poi__poi_detail_audit", None),
ETL("mart_waimaiaudit_waimai_audit.topic_dt__org_collect_swe", None),
ETL("mart_waimaiaudit_waimai_audit.topic_mo__org_collect_swe", None),
# ETL("hmart_waimai_crm.mix_dt_poi__detail_audit", ETL("mart_waimai_poiapp.app_dt__poi_org_audit", None, "-v --delta 2"), "-v --delta 2"),
ETL("hmart_waimai_crm.mix_mo_poi__poi_detail_audit", ETL("hmart_waimai_crm.eagle_hive2es__index_mo_poi_detail_audit", None))
]


def not_done(tasks):
    length = len(tasks)
    null_count = 0
    for task in tasks:
        if task is None:
            null_count += 1
    return null_count != length


def update_status(etl):
    uri = "/exapi/executor_plans/%s/status" % etl.exec_id
    headers = ba_headers("GET", uri)
    response = get(host="xt.sankuai.com", uri=uri, headers=headers)
    logging.info("查询执行状态[etl_name=%s][exec_id=%s][response=%s]",etl.name, etl.exec_id, response)
    if (response['code'] == 200) and (response['data']['status'] == 30):
        etl.status = 2

def submit(etl):
    """
    etl_name,exec_args,exec_reason
    :param etl:
    :return:
    """
    request_entity = {'etl_name': etl.name, 'exec_args': etl.args, 'exec_reason': '手动执行ETL:execute etl manually.'}
    uri = "/exapi/executor_plans"
    headers = ba_headers('POST', uri)
    headers['Content-Type'] = 'application/json'
    headers['Content-Encoding'] = 'utf-8'
    response = post(host="xt.sankuai.com", port=80, uri=uri, body=request_entity, headers=headers)
    if response['code'] == 200:
        etl.status = 1
        etl.exec_id = response['data']['id']
    logging.info("提交执行计划[etl_name=%s][exec_id=%s][response=%s]", etl.name, etl.exec_id, response)


def run():
    loop = True
    while loop:
        for i in range(0, len(tasks)):
            if tasks[i] is None:
                continue

        for i in range(0, len(tasks)):
            if tasks[i] is None:
                continue
            # 如果已提交,则更新执行状态
            if tasks[i].status == 1:
                update_status(tasks[i])

            # 未提交
            if tasks[i].status == 0:
                submit(tasks[i])
            # 如果执行完成
            elif tasks[i].status == 2:
                logging.info("执行完毕[etl_name=%s][exec_id=%s]", tasks[i].name, tasks[i].exec_id)
                tasks[i] = tasks[i].next
                if tasks[i] is not None:
                    submit(tasks[i])
        loop = not_done(tasks)
        if loop:
            time.sleep(10)
if __name__ == '__main__':
    run()
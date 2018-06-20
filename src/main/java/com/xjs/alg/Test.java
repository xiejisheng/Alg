package com.xjs.alg;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.primitives.Ints;

public class Test {
	public static void main(String[] args) {
        String dataAfterFormat = "1.3940";
        int indexOfDot = dataAfterFormat.indexOf(".");
        if (dataAfterFormat.length() - indexOfDot - 1  < 4) {
            dataAfterFormat = Joiner.on("").join(dataAfterFormat, Strings.repeat("0", 4 - (dataAfterFormat.length() - indexOfDot - 1)));
        }
        String prefix = Joiner.on("").join(dataAfterFormat.substring(0, indexOfDot), dataAfterFormat.substring(indexOfDot + 1, indexOfDot + 3));
        String commPrefix = Ints.tryParse(prefix).toString();
        dataAfterFormat = Joiner.on("").skipNulls().join(commPrefix, ".", dataAfterFormat.substring(dataAfterFormat.length() - 2, dataAfterFormat.length()), "%");
    }
}

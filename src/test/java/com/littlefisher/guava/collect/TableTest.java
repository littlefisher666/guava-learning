package com.littlefisher.guava.collect;

import java.util.Map;

import junit.framework.TestCase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TableTest {

    private static Logger logger = LogManager.getLogger(TableTest.class);

    /**
     * 二维表结构，Table中的泛型，分别对应横坐标、纵坐标、值
     */
    @Test
    public void test() {
        Table<Integer, Integer, String> table = HashBasedTable.create();
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 5; column++) {
                table.put(row, column, "value of cell (" + row + "," + column + ")");
            }
        }
        for (int row = 0; row < table.rowMap().size(); row++) {
            Map<Integer, String> rowData = table.row(row);
            for (int column = 0; column < rowData.size(); column++) {
                logger.debug("cell(" + row + "," + column + ") value is:" + rowData.get(column));
            }
        }
    }
}

package com.syswin.guava.collect;

import java.util.Map;

import junit.framework.TestCase;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class TableTest extends TestCase{

	public void test(){
		Table<Integer, Integer, String> table = HashBasedTable.create();
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 5; column++) {
                table.put(row, column, "value of cell (" + row + "," + column + ")");
            }
        }
        for (int row=0;row<table.rowMap().size();row++) {
            Map<Integer,String> rowData = table.row(row);
            for (int column =0;column < rowData.size(); column ++) {
                System.out.println("cell(" + row + "," + column + ") value is:" + rowData.get(column));
            }
        }
	}
}

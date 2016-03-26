package com.github.andyshaox.jdbc;

public enum SqlType {
    /** a DDL statement */
    EXECUTION , /** SELECT statement */
    QUERY , /** INSERT,UPDATE,DELETE statement */
    UPDATE;
}

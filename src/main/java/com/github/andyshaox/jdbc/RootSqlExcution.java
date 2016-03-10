package com.github.andyshaox.jdbc;

import java.lang.reflect.Method;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Mar 10, 2016<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class RootSqlExcution implements SqlExecution{
    private SqlAssembly assembly = SqlAssembly.DEFAUL;
    private SqlExecution excution = null;

    @Override
    public Object invoke(Dao dao , Method processMethod , String excutableSql , Object... args) {
        excutableSql = this.assembly.assemble(dao , processMethod , args);
        if (this.excution != null) return this.excution.invoke(dao , processMethod , excutableSql , args);
        return null;
    }

    public void setAssembly(SqlAssembly assembly) {
        this.assembly = assembly;
    }

    public void setExcution(SqlExecution excution) {
        this.excution = excution;
    }
}

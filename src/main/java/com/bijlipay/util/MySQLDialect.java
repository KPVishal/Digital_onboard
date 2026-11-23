package com.bijlipay.util;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.DateType;

public class MySQLDialect extends MySQL8Dialect {

    public MySQLDialect() {
        super();
        registerFunction("sub_date", new SQLFunctionTemplate(DateType.INSTANCE, "SUBDATE(?1, INTERVAL ?2 ?3)"));
    }

}

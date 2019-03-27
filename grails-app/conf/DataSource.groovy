dataSource {
    pooled = true
    driverClassName = 'com.mysql.jdbc.Driver'
    username = 'root'
    password = 'root'
    dialect = org.hibernate.dialect.MySQL5InnoDBDialect
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
    logSql=true
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "none"

            url = 'jdbc:mysql://localhost:3306/test?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false' //local DB
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}

package com.example.homework.user;
import org.apache.ibatis.jdbc.SQL;
public class UserProvider{
    private final String tableName ="users";
    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("name","#{u.name}");
            VALUES("gender","#{u.gender}");
            VALUES("one_signal_id","#{u.oneSignalId}");
            VALUES("student_card_id","#{u.studentCardId}");
            VALUES("is_student","#{u.isStudent}");
            VALUES("is_deleted","FALSE");

        }}.toString();
    }
    public String buildSelectByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("id= #{id}");
        }}.toString();
    }
    public String buildDeleteByIdSql(){
        return new SQL(){{
            DELETE_FROM(tableName);
            WHERE("id=#{id}");
        }}.toString();

    }
    public String buildUpdateIsDeletedByIdSql(){
        return new SQL(){{
            UPDATE(tableName);
            SET("is_deleted=#{status}");
            WHERE("id=#{id}");
        }}.toString();

    }
    public String buildSelectSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("is_deleted = FALSE");
            ORDER_BY("id DESC");
        }}.toString();
    }
    public String buildUpdateByIdSql(){
        return new SQL(){{
            UPDATE(tableName);
            SET("name = #{u.name}");
            SET("gender = #{u.gender}");
            WHERE("id=#{u.id}");
        }}.toString();
    }
    public String buildSelectByStudentIdCard(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("student_card_id = #{studentCardId}");

        }}.toString();
    }
    public String buildSelectByNameSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("name ILIKE'%' #{name} '%'");
        }}.toString();

    }
}

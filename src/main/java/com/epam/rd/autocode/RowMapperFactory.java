package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;

public class RowMapperFactory {

    public RowMapper<Employee> employeeRowMapper() {
        return resultSet -> {
            try {
                int id = resultSet.getInt("id");
                String first = resultSet.getString("firstname");
                String last = resultSet.getString("lastname");
                String middle = resultSet.getString("middlename");
                String position = resultSet.getString("position");
                double salary = resultSet.getDouble("salary");
                Date date = resultSet.getDate("hiredate");
                Position posit = Position.ANALYST;
                switch (position) {
                    case "PRESIDENT":
                        posit = Position.PRESIDENT;
                        break;
                    case "SALESMAN":
                        posit = Position.SALESMAN;
                        break;
                    case "CLERK":
                        posit = Position.CLERK;
                        break;
                    case "MANAGER":
                        posit = Position.MANAGER;
                        break;
                    case "ANALYST":
                        posit = Position.ANALYST;
                        break;
                }
                return new Employee(new BigInteger(Integer.toString(id)), new FullName(first, last, middle), posit,
                        date.toLocalDate(),
                        new BigDecimal(salary));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        };
    }
}

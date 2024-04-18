package com.mybatis.springminiproject.repository;
import com.mybatis.springminiproject.model.Expenses;
import com.mybatis.springminiproject.model.dto.request.ExpenseRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExpenseRepository {
    @Results(id = "expenseMapper", value = {
            @Result(property = "expenseId", column = "expense_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "categoryId", column = "category_id")
    })
    @Select("""
        SELECT * FROM expenses_tb ORDER BY ${column} ${select} LIMIT #{limit} OFFSET #{offset}
""")
    List<Expenses> getAllExpense(int offset, int limit, String column, String select);

    @Select("""
        INSERT INTO expenses_tb VALUES (
    default, #{expense.amount},#{expense.description}, #{expense.date},#{userId}, #{expense.categoryId}) RETURNING *
""")
    @ResultMap("expenseMapper")
    Expenses insertExpense(@Param("expense") ExpenseRequest expenseRequest, Integer userId);

    @Select("""
        SELECT * FROM expenses_tb WHERE expense_id = #{id}
    """)
    @ResultMap("expenseMapper")
    Expenses getExpenseById(Integer id);

    @Select("""
        UPDATE expenses_tb SET amount = #{expense.amount}, description = #{expense.description},date = #{expense.date},category_id = #{expense.categoryId} WHERE expense_id  = #{id} RETURNING *
    """)
    @ResultMap("expenseMapper")
    Expenses updateExpense(Integer id,@Param("expense") ExpenseRequest expenseRequest);

    @Delete("""
    DELETE FROM expenses_tb WHERE expense_id = #{id}
""")
    Boolean deleteExpense(Integer id);
}

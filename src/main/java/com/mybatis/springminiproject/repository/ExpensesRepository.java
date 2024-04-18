package com.mybatis.springminiproject.repository;

import com.mybatis.springminiproject.model.Expenses;
import com.mybatis.springminiproject.model.expenseResuest.ExpensesRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExpensesRepository {

    @Select("""
        SELECT *FROM expenses_tb LIMIT #{limit} OFFSET #{offset} ;
    """)
    @Results(id = "expensesMapper",value = {
            @Result(column = "expense_id",property = "expenseId"),
            @Result(column = "date",property = "dateTime"),
            @Result(column = "user_id",property = "userId"),
            @Result(column = "category_id",property = "categoryId")

    })
    List<Expenses> getAllExpense(Integer offset, Integer limit);

    @Select("""
    SELECT *FROM expenses_tb WHERE expense_id =#{expenseId};
    """)
    @ResultMap("expensesMapper")
    Expenses getExpenseById(Integer expenseId);

    @Select("""
        INSERT INTO expenses_tb(amount, description, date, user_id, category_id)
        VALUES (#{expenses.amount},#{expenses.description},#{expenses.dateTime},#{expenses.userId},#{expenses.categoryId}) RETURNING * ;
    """)
    @ResultMap("expensesMapper")
    Expenses insertExpense(@Param("expenses") ExpensesRequest expensesRequest);

    @Select("""
        DELETE FROM expenses_tb WHERE expense_id=#{expenseId};
    """)
    @ResultMap("expensesMapper")
    Expenses deleteExpense(Integer expenseId);

    @Select("""
        UPDATE expenses_tb 
        SET amount = #{expense.amount}, 
            description = #{expense.description},
            date = #{expense.dateTime}, 
            user_id = #{expense.userId}, 
            category_id = #{expense.categoryId} 
        WHERE expense_id = #{expenseId}
    """)
    @ResultMap("expensesMapper")
    Expenses updateExpenseById(@Param("expense") ExpensesRequest expensesRequest, @Param("expenseId") Integer expenseId);
}

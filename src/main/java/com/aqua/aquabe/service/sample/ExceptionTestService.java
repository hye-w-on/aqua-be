package com.aqua.aquabe.service.sample;

import java.sql.SQLException;
import java.io.IOException;

public interface ExceptionTestService {

    void callCheckedSQLException() throws SQLException;

    void callCheckedIOException() throws IOException;

    void callUnCheckedException();

    // Transaction Rollback Test
    void callCheckedExceptionAndRollbackFail() throws SQLException;

    void callUnCheckedExceptionAndRollback();

    void callCheckedExceptionAndRollback() throws SQLException;

    void callUnCheckedExceptionAndRollbackFail();

    void callExceptionTranslationAndRollback();

    // Propagation Transaction Rollback Test
    void callExceptionPropagationAndRollback1() throws SQLException;

    void callExceptionPropagationAndRollback2() throws SQLException;

    void callExceptionPropagationAndRollback3() throws SQLException;

}

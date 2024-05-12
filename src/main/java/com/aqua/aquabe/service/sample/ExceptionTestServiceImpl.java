package com.aqua.aquabe.service.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aqua.aquabe.model.employee.Employee;
import com.aqua.aquabe.repository.BbsPostRepository;
import com.aqua.aquabe.repository.EmployeeRepository;

import java.sql.SQLException;
import java.io.IOException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional // rollback을 위해 추가
@RequiredArgsConstructor
public class ExceptionTestServiceImpl implements ExceptionTestService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final EmployeeRepository employeeRepository;

    // test
    private final BbsPostRepository bbsPostRepository;

    @Override
    public void callCheckedSQLException() throws SQLException {
        // catch 나 throws 누락시 IDE에서 Syntax Error
        throw new SQLException();
    }

    @Override
    public void callCheckedIOException() throws IOException {
        // catch 나 throws 누락시 IDE에서 Syntax Error
        throw new IOException();
    }

    @Override
    public void callUnCheckedException() {
        // RuntimeException은 throws를 강제하지않음
        throw new RuntimeException();
    }

    @Override
    public void callCheckedExceptionAndRollbackFail() throws SQLException {
        // rollback 안됨, Exception 발생 후에도 "Checked"는 저장됨
        employeeRepository.save(Employee.builder()
                .name("Checked")
                .build());

        throw new SQLException();

    }

    @Override
    public void callUnCheckedExceptionAndRollback() {
        // rollback 됨, Exception 발생 후에 "UnChecked"는 저장 안됨
        employeeRepository.save(Employee.builder()
                .name("UnChecked")
                .build());

        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = { Exception.class })
    // rollbackFor에 Exception.class를 추가하면 Checked Exception 예외도 롤백 됨
    public void callCheckedExceptionAndRollback() throws SQLException {
        employeeRepository.save(Employee.builder()
                .name("Checked")
                .build());
        try {
            throw new SQLException();
        } catch (SQLException e) {
            // 예외를 catch 하면 rollback이 안됨. catch 하지말고 경계(?)로 던져줘야함. 상위에서는 catch해도 상관없음
            throw new SQLException();
        }

    }

    @Override
    public void callUnCheckedExceptionAndRollbackFail() {
        employeeRepository.save(Employee.builder()
                .name("UnChecked")
                .build());
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            e.printStackTrace();
            // 예외를 catch 하면 rollback이 안됨
            // catch 하지말고 Controller-Service의 경계로 던져줘야함
            // Controller에서 catch해도 상관없으나 UnCheckedException은 명시적 예외처리가 필요없음
            // 즉 RuntimeException을 발생시켜서 throw 하기만 하면 Rollback이 됨
        }
    }

    @Override
    public void callExceptionTranslationAndRollback() {
        employeeRepository.save(Employee.builder()
                .name("Checked")
                .build());
        try {
            throw new SQLException();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void callExceptionPropagationAndRollback1() throws SQLException {
        employeeRepository.save(Employee.builder()
                .name("Propagation1")
                .build());
        log.info("Propagation1");

        /* Case 1 */
        try {
            callExceptionPropagationAndRollback2();
        } catch (SQLException e) {
            // 한번이라도 예외전환하면 하위 트랜젝션 모두 롤백
            throw new RuntimeException();
        }

        /* Case 2 */
        // callExceptionPropagationAndRollback2();

        /* Case 3 */
        // try {
        // callExceptionPropagationAndRollback2();
        // } catch (RuntimeException e) {
        // // 상위 Service에서 예외를 catch하면 RuntimeException라도 하위 트랜젝션 모두 롤백 안됨(2,3 다 catch
        // 가능)
        // }

        /* Case 4 */
        // callExceptionPropagationAndRollback2();

    }

    @Override
    public void callExceptionPropagationAndRollback2() throws SQLException {
        employeeRepository.save(Employee.builder()
                .name("Propagation2")
                .build());
        log.info("Propagation2");

        /* Case 1 */
        callExceptionPropagationAndRollback3();

        /* Case 2 */
        // callExceptionPropagationAndRollback3();
        // 전체 트렌젝션 중 한번이라도 예외가 발생하면 관련 트랜젝션 모두 롤백 (3까지 롤백)
        // throw new RuntimeException();

        /* Case 3 */
        // callExceptionPropagationAndRollback3();
        // throw new RuntimeException();
        // 상위 Service에서 예외를 catch하면 하위 트랜젝션 모두 롤백 안됨 (1에서 catch 발생)

        /* Case 4 */
        // callExceptionPropagationAndRollback3();
        // throw new SQLException();

    }

    @Override
    public void callExceptionPropagationAndRollback3() throws SQLException {
        employeeRepository.save(Employee.builder()
                .name("Propagation3")
                .build());
        log.info("Propagation3");

        /* Case 1 */
        throw new SQLException();

        /* Case 2 */
        // 전체 트렌젝션 중 한번이라도 UnChecked 예외가 발생하면 하위 트랜젝션 모두 롤백 (상위에서 exception 발생)

        /* Case 3 */
        // 상위 Service에서 예외를 catch하면 하위 트랜젝션 모두 롤백 안됨 (1에서 UnChecked catch 발생)

        /* Case 4 */
        // 상위 Service에서 발생한 Checked 예외는하위 UnChecked 예외에 영향을 미치지않음 (1,2롤백 안됨,3롤백)
        // throw new RuntimeException();
    }

    // TODO: 테스트용 삭제
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
    public void callTrn() {
        bbsPostRepository.updateDisableBbsPost("1");
    }

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public void callTrn2() {
        callTrn();
        System.out.println("----result:" + bbsPostRepository.selectBbsPost(1));

    }
}

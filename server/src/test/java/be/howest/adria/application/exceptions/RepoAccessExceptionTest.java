package be.howest.adria.application.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class RepoAccessExceptionTest {

    private final Throwable cause = new Throwable();

    @Test
    void testExceptionGetsThrown(){
        assertThrows(RepoAccessException.class, this::throwException);
    }

    @Test
    void testExceptionHasCorrectMessage(){
        try{
          throwException();
          fail();
        }
        catch(RepoAccessException ex){
            assertEquals("Test exception", ex.getMessage());
        }
    }

    @Test
    void testExceptionHasCorrectCause(){
        try{
            throwExceptionWithCause();
            fail();
        }
        catch(RepoAccessException ex){
            assertEquals(cause, ex.getCause());
        }
    }

    @Test
    void testExceptionWithOnlyCause() {
        try {
            throwExceptionWithOnlyCause();
            fail();
        } catch (RepoAccessException ex) {
            assertEquals(cause, ex.getCause());
            assertEquals("java.lang.Throwable", ex.getMessage()); // Default message from Throwable.toString()
        }
    }

    private void throwException() {
        throw new RepoAccessException("Test exception");
    }

    private void throwExceptionWithCause() {
        throw new RepoAccessException("Test exception", cause);
    }

    private void throwExceptionWithOnlyCause() {
        throw new RepoAccessException(cause);
    }

}

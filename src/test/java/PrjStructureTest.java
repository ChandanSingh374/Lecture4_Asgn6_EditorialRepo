import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrjStructureTest {
    @BeforeEach
    void setUp() {

    }

    @Test
    void I1exists(){
        try {
            Class.forName("I1");
            assertTrue(true);
        } catch (ClassNotFoundException e) {
            assertTrue(false);
        }
    }

    @Test
    void I2exists(){
        try {
            Class.forName("I2");
            assertTrue(true);
        } catch (ClassNotFoundException e) {
            assertTrue(false);
        }
    }

    @Test
    void Iexists(){
        try {
            Class.forName("I");
            assertTrue(true);
        } catch (ClassNotFoundException e) {
            assertTrue(false);
        }
    }

    @Test
    void Cexists(){
        try {
            Class.forName("C");
            assertTrue(true);
        } catch (ClassNotFoundException e) {
            assertTrue(false);
        }
    }

    public void I1FunMethodExists() throws Exception {
        try {
            Class<?> i1Class = Class.forName("I1");
            Method method = i1Class.getDeclaredMethod("fun");
            assertTrue(method != null);
            assertEquals(method.getReturnType(), void.class);
            assertEquals(method.getParameterCount(), 0);
        } catch (ClassNotFoundException e) {
            // I1 is not defined, so skip the test
            fail("Exception thrown: " + e);
        }
    }

    @Test
    public void I1Fun1MethodExists() throws Exception {
        try {
            Class<?> i1Class = Class.forName("I1");
            Method method = i1Class.getDeclaredMethod("fun1");
            assertTrue(method != null);
            assertEquals(method.getReturnType(), void.class);
            assertEquals(method.getParameterCount(), 0);
        } catch (ClassNotFoundException e) {
            // I1 is not defined, so skip the test
            fail("Exception thrown: " + e);
        }
    }

    @Test
    public void I2FunMethodExists() throws Exception {
        try {
            Class<?> i2Class = Class.forName("I2");
            Method method = i2Class.getDeclaredMethod("fun");
            assertTrue(method != null);
            assertEquals(method.getReturnType(), void.class);
            assertEquals(method.getParameterCount(), 0);
        } catch (ClassNotFoundException e) {
            // I2 is not defined, so skip the test
            fail("Exception thrown: " + e);
        }
    }

    @Test
    public void I2Fun2MethodExists() throws Exception {
        try {
            Class<?> i2Class = Class.forName("I2");
            Method method = i2Class.getDeclaredMethod("fun2");
            assertTrue(method != null);
            assertEquals(method.getReturnType(), void.class);
            assertEquals(method.getParameterCount(), 0);
        } catch (ClassNotFoundException e) {
            // I2 is not defined, so skip the test
            fail("Exception thrown: " + e);
        }
    }

    public void testIExtendsI1() throws Exception {
        try {
            Class<?> i1Class = Class.forName("I1");
            Class<?> iClass = Class.forName("I");
            assertTrue(i1Class.isAssignableFrom(iClass));
        } catch (ClassNotFoundException e) {
            // I1 or I is not defined, so skip the test
            fail("Exception thrown: " + e);
        }
    }

    @Test
    public void testIExtendsI2() throws Exception {
        try {
            Class<?> i2Class = Class.forName("I2");
            Class<?> iClass = Class.forName("I");
            assertTrue(i2Class.isAssignableFrom(iClass));
        } catch (ClassNotFoundException e) {
            // I2 is not defined, so skip the test
            fail("Exception thrown: " + e);
        }
    }

    @Test
    public void testIHasNoMethods() throws Exception {
        try {
            Class<?> iClass = Class.forName("I");
            Method[] methods = iClass.getDeclaredMethods();
            assertEquals(0, methods.length);
        } catch (ClassNotFoundException e) {
            // I is not defined, so skip the test
            fail("Exception thrown: " + e);
        }
    }

    @Test
    public void testCImplementsI() throws Exception {
        try {
            Class<?> cClass = Class.forName("C");
            Class<?> iClass = Class.forName("I");

            Class<?>[] interfaces = cClass.getInterfaces();
            boolean foundI = false;
            for (Class<?> i : interfaces) {
                if (i.equals(iClass)) {
                    foundI = true;
                    break;
                }
            }
            assertTrue(foundI);

            Method[] methods = cClass.getMethods();
            boolean foundFun1 = false;
            boolean foundFun2 = false;
            boolean foundFun = false;
            for (Method method : methods) {
                if (Modifier.isAbstract(method.getModifiers())) {
                    continue;
                }
                if (method.getName().equals("fun1") && method.getReturnType().equals(Void.TYPE) && method.getParameterCount() == 0) {
                    foundFun1 = true;
                } else if (method.getName().equals("fun2") && method.getReturnType().equals(Void.TYPE) && method.getParameterCount() == 0) {
                    foundFun2 = true;
                } else if (method.getName().equals("fun") && method.getReturnType().equals(Void.TYPE) && method.getParameterCount() == 0) {
                    foundFun = true;
                }
            }
            assertTrue(foundFun1);
            assertTrue(foundFun2);
            assertTrue(foundFun);
        } catch (ClassNotFoundException e) {
            fail("Exception thrown: " + e);
        }
    }
}

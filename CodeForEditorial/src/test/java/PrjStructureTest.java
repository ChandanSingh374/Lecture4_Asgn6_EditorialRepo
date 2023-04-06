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
        Method method = I1.class.getDeclaredMethod("fun");
        assertTrue(method != null);
        assertEquals(method.getReturnType(), void.class);
        assertEquals(method.getParameterCount(), 0);
    }

    @Test
    public void I1Fun1MethodExists() throws Exception {
        Method method = I1.class.getDeclaredMethod("fun1");
        assertTrue(method != null);
        assertEquals(method.getReturnType(), void.class);
        assertEquals(method.getParameterCount(), 0);
    }

    public void I2FunMethodExists() throws Exception {
        Method method = I2.class.getDeclaredMethod("fun");
        assertTrue(method != null);
        assertEquals(method.getReturnType(), void.class);
        assertEquals(method.getParameterCount(), 0);
    }

    @Test
    public void I2Fun2MethodExists() throws Exception {
        Method method = I2.class.getDeclaredMethod("fun2");
        assertTrue(method != null);
        assertEquals(method.getReturnType(), void.class);
        assertEquals(method.getParameterCount(), 0);
    }

    public void testIExtendsI1() throws Exception {
        assertTrue(I1.class.isAssignableFrom(I.class));
    }

    @Test
    public void testIExtendsI2() throws Exception {
        assertTrue(I2.class.isAssignableFrom(I.class));
    }

    @Test
    public void testIHasNoMethods() throws Exception {
        Method[] methods = I.class.getDeclaredMethods();
        assertEquals(0, methods.length);
    }

    @Test
    public void testCImplementsI() throws Exception {
        Class<?>[] interfaces = C.class.getInterfaces();
        boolean foundI = false;
        for (Class<?> i : interfaces) {
            if (i.equals(I.class)) {
                foundI = true;
                break;
            }
        }
        assertTrue(foundI);

        Method[] methods = C.class.getMethods();
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
    }

}
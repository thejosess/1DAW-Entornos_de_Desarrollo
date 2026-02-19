/* Tipo de test? */
public class TestUnitTestnumOperationsTest {
    @Test
    public void scenario1() {
        UnitTestnumOperations mytest = new UnitTestnumOperations();
        int value = mytest.compare(3, 2);
        Assertions.assertEquals(1, value);
    }
    @Test
    public void scenario2() {
        UnitTestnumOperations mytest = new UnitTestnumOperations();
        int value = mytest.compare(2, 3);
        Assertions.assertEquals(-1, value);
    }
    @Test
    public void scenario3() {
        UnitTestnumOperations mytest = new UnitTestnumOperations();
        int value = mytest.compare(2, 2);
        Assertions.assertEquals(0, value);
    }
}
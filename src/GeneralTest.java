public class GeneralTest {
    enum Test {
        ONE, TWO;

        private int num;

        public void changeNum() {
            num++;
        }
    }

    public static void main(String[] args) {
        Test one1 = Test.ONE;
        Test one2 = Test.ONE;
        System.out.println("one1 and one2 before changeNum are equal: " + (one1 == one2));
        one1.changeNum();
        System.out.println(one1 == one2);
    }
}

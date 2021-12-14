public class Bruce {

    volatile byte[] b = new byte[1024 * 1024 * 10];
    @Override
    protected void finalize() throws Throwable {
//        super.finalize();
        System.out.println("bruce GC");
    }
}

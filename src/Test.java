import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();

        test.strongRef(); // -Xms1m -Xmx1m

        // 以下用 -Xms20m -Xmx20m
//        test.softRef();
//        test.softRef();
//        test.phantomRef();
        test.xxx();
    }

    private void strongRef() {
        Bruce bruce = new Bruce();
//        bruce = null;
        System.out.println("ooo");
//        System.gc();
    }

    private void xxx() {
        System.out.println("xxx");
        System.gc();
    }

    private void softRef() {
        byte[] bytes1 = new byte[1024 * 1024 * 10];
        SoftReference<byte[]> byteArrayRef = new SoftReference<>(bytes1);
        System.out.println(byteArrayRef.get());
        byteArrayRef = null;
        System.gc();
        System.out.println(byteArrayRef.get());
//        byte[] bytes = new byte[1024 * 1024 * 10];
        System.out.println(byteArrayRef.get());
        System.out.println(bytes1);
    }

    private void weakRef() {
        WeakReference<byte[]> byteArrayRef = new WeakReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(byteArrayRef.get());
        System.gc();
        System.out.println(byteArrayRef.get());
    }

    private void phantomRef() {
        // 虛引用要和 ReferenceQueue 一起使用，當準備要回收時，發現還有虛引用，就會將此引用放到 ReferenceQueue 裡
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<byte[]> reference = new PhantomReference<>(new byte[1], queue);
        System.out.println(reference.get());


    }
}

package com.zsc.example.nobody.jsrsey;

import com.sun.jersey.spi.scanning.AnnotationScannerListener;

/**
 * @program: nobody_demo
 * @description:
 * @author: zhangsc
 * @create: 2021-10-22 15:42
 **/
public class ErrInfoScannerListener extends AnnotationScannerListener {

    public ErrInfoScannerListener() {
        super(Err.class, Info.class);
    }

    /**
     * Create a scanning listener to check for Java classes in Java
     * class files annotated with {@link Err} or {@link Info}.
     *
     * @param classloader the class loader to use to load Java classes that
     *        are annotated with any one of the annotations.
     */
    public ErrInfoScannerListener(ClassLoader classloader) {
        super(classloader, Err.class, Info.class);
    }
}
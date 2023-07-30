//package com.tdk.method_compiler
//
//import com.google.auto.service.AutoService
//import com.tdk.method_compiler.ProcessorConfig
//import com.tdk.method_annotations.MethodProvider
//import com.squareup.javapoet.MethodSpec
//import com.squareup.javapoet.TypeSpec
//import com.squareup.javapoet.JavaFile
//import java.io.IOException
//import javax.annotation.processing.*
//import javax.lang.model.SourceVersion
//import javax.lang.model.element.Modifier
//import javax.lang.model.element.TypeElement
//import javax.lang.model.util.Elements
//import javax.lang.model.util.Types
//import javax.tools.Diagnostic
//
///**
// * 同学们注意：编码此类，记住就是一个字（细心，细心，细心），出了问题debug真的不好调试
// */
//// AutoService则是固定的写法，加个注解即可
//// 通过auto-service中的@AutoService可以自动生成AutoService注解处理器，用来注册
//// 用来生成 META-INF/services/javax.annotation.processing.Processor 文件
//@AutoService(Processor::class) // 允许/支持的注解类型，让注解处理器处理
//@SupportedAnnotationTypes(ProcessorConfig.AROUTER_PACKAGE) // 指定JDK编译版本
//@SupportedSourceVersion(SourceVersion.RELEASE_7) // 注解处理器接收的参数
//@SupportedOptions(ProcessorConfig.OPTIONS, ProcessorConfig.APT_PACKAGE)
//class ARouterProcessor : AbstractProcessor() {
//    // 操作Element的工具类（类，函数，属性，其实都是Element）
//    private var elementTool: Elements? = null
//
//    // type(类信息)的工具类，包含用于操作TypeMirror的工具方法
//    private val typeTool: Types? = null
//
//    // Message用来打印 日志相关信息
//    lateinit var messager: Messager
//
//    // 文件生成器， 类 资源 等，就是最终要生成的文件 是需要Filer来完成的
//    private var filer: Filer? = null
//    private var options: String? = null
//
//    // 做初始化工作，就相当于 Activity中的 onCreate函数一样的作用
//    @Synchronized
//    override fun init(processingEnvironment: ProcessingEnvironment) {
//        super.init(processingEnvironment)
//        elementTool = processingEnvironment.elementUtils
//        messager = processingEnvironment.messager
//        filer = processingEnvironment.filer
//
//        // 只有接受到 App壳 传递过来的书籍，才能证明我们的 APT环境搭建完成
//        options = processingEnvironment.options[ProcessorConfig.OPTIONS]
//        val aptPackage = processingEnvironment.options[ProcessorConfig.APT_PACKAGE]
//        messager.printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>> options:$options")
//        messager.printMessage(Diagnostic.Kind.NOTE, ">>>>>>>>>>>>>>>>>>>>>> aptPackage:$aptPackage")
//        if (options != null && aptPackage != null) {
//            messager.printMessage(Diagnostic.Kind.NOTE, "APT 环境搭建完成....")
//        } else {
//            messager.printMessage(
//                Diagnostic.Kind.NOTE,
//                "APT 环境有问题，请检查 options 与 aptPackage 为null..."
//            )
//        }
//    }
//
//    /**
//     * 相当于main函数，开始处理注解
//     * 注解处理器的核心方法，处理具体的注解，生成Java文件
//     *
//     * @param set              使用了支持处理注解的节点集合
//     * @param roundEnvironment 当前或是之前的运行环境,可以通过该对象查找的注解。
//     * @return true 表示后续处理器不会再处理（已经处理完成）
//     */
//    override fun process(set: Set<TypeElement?>, roundEnvironment: RoundEnvironment): Boolean {
//        if (set.isEmpty()) {
//            messager!!.printMessage(Diagnostic.Kind.NOTE, "并没有发现 被@ARouter注解的地方呀")
//            return false
//        }
//
//        // 获取所有被 @ARouter 注解的 元素集合
//        val elements = roundEnvironment.getElementsAnnotatedWith(
//            MethodProvider::class.java
//        )
//        // 遍历所有的类节点
//        for (element in elements) {
//            // 获取类节点，获取包节点 （com.xiangxue.xxxxxx）
//            val packageName = elementTool!!.getPackageOf(element).qualifiedName.toString()
//
//            // 获取简单类名，例如：MainActivity
//            val className = element.simpleName.toString()
//            messager!!.printMessage(
//                Diagnostic.Kind.NOTE,
//                ">>>>>>>>>>>>>> 被@ARetuer注解的类有：$className"
//            ) // 打印出 就证明APT没有问题
//
//            // 下面是练习 JavaPoet
//            /**
//             * package com.example.helloworld;
//             *
//             * public final class HelloWorld {
//             * public static void main(String[] args) {
//             * System.out.println("Hello, JavaPoet!");
//             * }
//             * }
//             */
//
//            // 下毒，现在是 无法生成 【作业】
//
//            // 1.方法
//            /*MethodSpec mainMethod = MethodSpec.methodBuilder("main")
//                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
//                    .returns(void.class)
//                    .addParameter(String[].class, "args")
//                    .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
//                    .build();
//
//            // 2.类
//            TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
//                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
//                    .addMethod(mainMethod)
//                    .build();
//
//            // 3.包
//            JavaFile packagef = JavaFile.builder("com.derry.study", helloWorld).build();
//
//            // 去生成
//            try {
//                packagef.writeTo(filer);
//            } catch (IOException e) {
//                e.printStackTrace();
//                messager.printMessage(Diagnostic.Kind.NOTE, "生成失败，请检查代码...");
//            }*/
//
//            // 先JavaPoet 写一个简单示例，方法--->类--> 包，是倒序写的思路哦
//            /*
//             package com.example.helloworld;
//
//             public final class HelloWorld {
//
//                 public static void main(String[] args) {
//                    System.out.println("Hello, JavaPoet!");
//                 }
//
//             }
//
//             */
//            // 方法
//            val mainMethod = MethodSpec.methodBuilder("main")
//                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
//                .returns(Void.TYPE)
//                .addParameter(Array<System>::class.java, "args") // 增加main方法里面的内容
//                .addStatement("\$T.out.println(\$S)", System::class.java, "AAAAAAAAAAA!")
//                .build()
//            // 类
//            val testClass = TypeSpec.classBuilder("Test")
//                .addMethod(mainMethod)
//                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
//                .build()
//
//            // 包
//            // Program type already present: com.xiangxue.test22.Test
//            val packagef = JavaFile.builder("com.xiangxue.test22", testClass).build()
//            try {
//                packagef.writeTo(filer)
//            } catch (e: IOException) {
//                e.printStackTrace()
//                messager!!.printMessage(Diagnostic.Kind.NOTE, "生成Test文件时失败，异常:" + e.message)
//            }
//        }
//        return true // 坑：必须写返回值，表示处理@ARouter注解完成
//    }
//}
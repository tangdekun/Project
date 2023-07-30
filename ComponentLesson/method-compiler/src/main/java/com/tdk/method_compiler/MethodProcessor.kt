package com.tdk.method_compiler

import com.google.auto.service.AutoService
import com.squareup.javapoet.ClassName
import com.squareup.kotlinpoet.*
import com.tdk.method_annotations.MethodProvider
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.lang.model.util.Types
import javax.tools.Diagnostic

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_11)
@SupportedOptions(ProcessorConfig.OPTIONS, "versionCode")
class MethodProcessor : AbstractProcessor() {


    // 操作Element的工具类（类，函数，属性，其实都是Element）
    private var elementTool: Elements? = null

    // type(类信息)的工具类，包含用于操作TypeMirror的工具方法
    private val typeTool: Types? = null

    // Message用来打印 日志相关信息
    private var messager: Messager? = null

    // 文件生成器， 类 资源 等，就是最终要生成的文件 是需要Filer来完成的
    private var filer: Filer? = null

    private var options: String? = null

    override fun init(processingEnvironment: ProcessingEnvironment?) {
        super.init(processingEnvironment)
        if (processingEnvironment == null) {
            return
        }
        // 只有接受到 App壳 传递过来的书籍，才能证明我们的 APT环境搭建完成
        elementTool = processingEnvironment.elementUtils
        messager = processingEnvironment.messager
        filer = processingEnvironment.filer
        // 只有接受到 App壳 传递过来的书籍，才能证明我们的 APT环境搭建完成
        options = processingEnvironment.options[ProcessorConfig.OPTIONS]
        val versionCode: String? = processingEnvironment.options["versionCode"]
        printMessage(">>>>>>>>>>>>>>>>>>>>>> options:$options")
        printMessage(">>>>>>>>>>>>>>>>>>>>>> versionCode:$versionCode")
        if (options != null && versionCode != null) {
            printMessage("APT 环境搭建完成....")
        } else {
            printMessage("APT 环境有问题，请检查 options 与 aptPackage 为null...")
        }
    }

    //process 方法最少执行两次，最多无数次。最后一次执行可以视为Finish结束通知，执行收尾工作。
    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
        printMessage("process 注解成功")
        if (p0?.isEmpty() == true) {
            printMessage("TypeElementSet is null")
        } else {
            p0?.forEach {
                printMessage(it.toString())
            }
        }
        val elementSet = p1?.getElementsAnnotatedWith(MethodProvider::class.java)
        elementSet?.forEach {
            printMessage(it.toString())
            /*
class Greeter(val name: String) {
  fun greet() {
    println("""Hello, $name""")
  }
}

fun main(vararg args: String) {
  Greeter(args[0]).greet()
}
}*/
            val greeterClass = ClassName("", "Greeter")
            val file = FileSpec.builder("", "HelloWorld")
                .addType(
                    TypeSpec.classBuilder("Greeter")
                        .primaryConstructor(
                            FunSpec.constructorBuilder()
                                .addParameter("name", String::class)
                                .build()
                        )
                        .addProperty(
                            PropertySpec.builder("name", String::class)
                                .initializer("name")
                                .build()
                        )
                        .addFunction(
                            FunSpec.builder("greet")
                                .addStatement("println(%P)", "Hello, \$name")
                                .build()
                        )
                        .build()
                )
                .addFunction(
                    FunSpec.builder("main")
                        .addParameter("args", String::class, KModifier.VARARG)
                        .addStatement("%T(args[0]).greet()", greeterClass)
                        .build()
                )
                .build()

            file.writeTo(System.out)
            file.writeTo(File("E:\\Project\\ComponentLesson\\generated_code"))

        }
        return true //false 不处理   true 处理完成
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(MethodProvider::class.java.canonicalName)
    }

    fun printMessage(msg: String) {
        messager?.printMessage(Diagnostic.Kind.NOTE, msg)
    }
}
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class LibrarySharing : Plugin<Project> {

    override fun apply(project: Project): Unit = project.run {
        if (extensions.findByType<Extensions>() == null) {
            extensions.add("kmm-implementation", Extensions(this))
        }
    }
}

public class Extensions(private val project: Project) {

    public fun implementations(
        common: List<Any> = emptyList(),
        android: List<Any> = emptyList(),
        ios: List<Any> = emptyList(),
        linux: List<Any> = emptyList(),
        macos: List<Any> = emptyList(),
        windows: List<Any> = emptyList()
    ) {
        project.extensions.findByType<KotlinMultiplatformExtension>()?.apply {
            sourceSets {
                findByName("commonMain")
                    ?.apply { dependencies { common.onEach { implementation(it) } } }
                findByName("androidMain")
                    ?.apply { dependencies { android.onEach { implementation(it) } } }
                findByName("iosMain")
                    ?.apply { dependencies { ios.onEach { implementation(it) } } }
                findByName("windowsMain")
                    ?.apply { dependencies { windows.onEach { implementation(it) } } }
                findByName("linuxMain")
                    ?.apply { dependencies { linux.onEach { implementation(it) } } }
                findByName("macosMain")
                    ?.apply { dependencies { macos.onEach { implementation(it) } } }
            }
        }
    }

    public fun implementation(
        common: Any? = null,
        android: Any? = null,
        ios: Any? = null,
        windows: Any? = null,
        linux: Any? = null,
        macos: Any? = null
    ) {
        project.extensions.findByType<KotlinMultiplatformExtension>()?.apply {
            sourceSets {
                findByName("commonMain")
                    ?.apply { dependencies { common?.let { implementation(it) } } }
                findByName("androidMain")
                    ?.apply { dependencies { android?.let { implementation(it) } } }
                findByName("iosMain")
                    ?.apply { dependencies { ios?.let { implementation(it) } } }
                findByName("windowsMain")
                    ?.apply { dependencies { windows?.let { implementation(it) } } }
                findByName("linuxMain")
                    ?.apply { dependencies { linux?.let { implementation(it) } } }
                findByName("macosMain")
                    ?.apply { dependencies { macos?.let { implementation(it) } } }
            }
        }
    }
}
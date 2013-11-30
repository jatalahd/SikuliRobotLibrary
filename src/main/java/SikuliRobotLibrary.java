import org.robotframework.javalib.library.AnnotationLibrary;

public class SikuliRobotLibrary extends AnnotationLibrary {
    public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";
    public static final String ROBOT_LIBRARY_VERSION = "1.0";
    
    public SikuliRobotLibrary() {
        super("org/robotframework/sikulirobotlibrary/SikuliKeywords.class");
    }
}

package cz.cvut.fit.hybljan2.apitestingcg.configuration.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: Jan Hýbl
 * Date: 9.1.12
 * Time: 14:46
 */
@XmlRootElement(name = "generator")
public class GeneratorConfiguration {

    // name of object used to call non-static methods
    private String instanceIdentifier = "instance";
    // identifier of "Caller" - Method that call some method from tested library.
    private String methodCallIdentifier = "%s";
    private String methodNullCallIdentifier = "%sNullCall";
    private String instantiatorClassIdentifier = "%sUser";
    private String extenderClassIdentifier = "%sExtender";
    private String implementerClassIdentifier = "%sImplementer";
    private String annotationClassIdentifier = "%sAnnotation";
    // name of method that is used for creating instance of object (test constructor)
    private String createInstanceIdentifier = "create%s";
    // same as previous, but with null params.
    private String createNullInstanceIdentifier = "create%sNull";
    // same as createInstanceIdentifier, but method test creating of super-class.
    private String createSuperInstanceIdentifier = "create%s";
    // same as createInstanceIdentifier, but method test creating of implemented interface instance.
    private String createInterfaceInstanceIdentifier = "create%s";
    // name of method which test fields in class.
    private String fieldTestIdentifier = "fields";
    // name of field used for fields tests.
    private String fieldTestVariableIdentifier = "%sValue";
    // name of the variable for exceptions
    private String exceptionVariableName = "e";


    @XmlElement(name = "instance-identifier")
    public String getInstanceIdentifier() {
        return instanceIdentifier;
    }

    public void setInstanceIdentifier(String instanceIdentifier) {
        this.instanceIdentifier = instanceIdentifier;
    }

    @XmlElement(name = "method-call-identifier")
    public String getMethodCallIdentifier() {
        return methodCallIdentifier;
    }

    public void setMethodCallIdentifier(String methodCallIdentifier) {
        this.methodCallIdentifier = methodCallIdentifier;
    }

    @XmlElement(name = "method-null-call-identifier")
    public String getMethodNullCallIdentifier() {
        return methodNullCallIdentifier;
    }

    public void setMethodNullCallIdentifier(String methodNullCallIdentifier) {
        this.methodNullCallIdentifier = methodNullCallIdentifier;
    }

    @XmlElement(name = "user-class-identifier")
    public String getInstantiatorClassIdentifier() {
        return instantiatorClassIdentifier;
    }

    public void setInstantiatorClassIdentifier(String instantiatorClassIdentifier) {
        this.instantiatorClassIdentifier = instantiatorClassIdentifier;
    }

    @XmlElement(name = "instantiator-class-identifier")
    public String getUserClassIdentifier() {
        return instantiatorClassIdentifier;
    }

    public void setUserClassIdentifier(String instantiatorClassIdentifier) {
        this.instantiatorClassIdentifier = instantiatorClassIdentifier;
    }

    @XmlElement(name = "extender-class-identifier")
    public String getExtenderClassIdentifier() {
        return extenderClassIdentifier;
    }

    public void setExtenderClassIdentifier(String extenderClassIdentifier) {
        this.extenderClassIdentifier = extenderClassIdentifier;
    }

    @XmlElement(name = "implementer-class-identifier")
    public String getImplementerClassIdentifier() {
        return implementerClassIdentifier;
    }

    public void setImplementerClassIdentifier(String implementerClassIdentifier) {
        this.implementerClassIdentifier = implementerClassIdentifier;
    }

    @XmlElement(name = "create-instance-identifier")
    public String getCreateInstanceIdentifier() {
        return createInstanceIdentifier;
    }

    public void setCreateInstanceIdentifier(String createInstanceIdentifier) {
        this.createInstanceIdentifier = createInstanceIdentifier;
    }

    @XmlElement(name = "create-null-instance-identifier")
    public String getCreateNullInstanceIdentifier() {
        return createNullInstanceIdentifier;
    }

    public void setCreateNullInstanceIdentifier(String createNullInstanceIdentifier) {
        this.createNullInstanceIdentifier = createNullInstanceIdentifier;
    }

    @XmlElement(name = "create-super-instance-identifier")
    public String getCreateSuperInstanceIdentifier() {
        return createSuperInstanceIdentifier;
    }

    public void setCreateSuperInstanceIdentifier(String createSuperInstanceIdentifier) {
        this.createSuperInstanceIdentifier = createSuperInstanceIdentifier;
    }

    @XmlElement(name = "create-interface-instance-identifier")
    public String getCreateInterfaceInstanceIdentifier() {
        return createInterfaceInstanceIdentifier;
    }

    public void setCreateInterfaceInstanceIdentifier(String createInterfaceInstanceIdentifier) {
        this.createInterfaceInstanceIdentifier = createInterfaceInstanceIdentifier;
    }

    @XmlElement(name = "field-test-identifier")
    public String getFieldTestIdentifier() {
        return fieldTestIdentifier;
    }

    public void setFieldTestIdentifier(String fieldTestIdentifier) {
        this.fieldTestIdentifier = fieldTestIdentifier;
    }

    @XmlElement(name = "field-test-variable-identifier")
    public String getFieldTestVariableIdentifier() {
        return fieldTestVariableIdentifier;
    }

    public void setFieldTestVariableIdentifier(String fieldTestVariableIdentifier) {
        this.fieldTestVariableIdentifier = fieldTestVariableIdentifier;
    }

    @XmlElement(name = "annotation-class-identifier")
    public String getAnnotationClassIdentifier() {
        return annotationClassIdentifier;
    }

    public void setAnnotationClassIdentifier(String annotationClassIdentifier) {
        this.annotationClassIdentifier = annotationClassIdentifier;
    }

    @XmlElement(name = "exception-variable-identifier")
    public String getExceptionVariableName() {
        return exceptionVariableName;
    }

    public void setExceptionVariableName(String exceptionVariableName) {
        this.exceptionVariableName = exceptionVariableName;
    }
}

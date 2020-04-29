import java.util.Collection;

public class XMLSerializerHelper implements SerializerHelper {
    @Override
    public void serializeClass(String indentation, StringBuilder result, Object fieldObject, ClassSerializer classSerializer, String fieldName) {
        result.append(indentation).append('\t');
        result.append(classSerializer.getFieldsWithIndentation(fieldObject, indentation + '\t'));
        result.append("\n");
    }

    @Override
    public void serializePrimitiveTypeOrWrapperOrString(String indentation, StringBuilder result, Object fieldObject, String fieldName) {
        result.append(indentation).append('\t').append("<").append(fieldName).append("> ").append(fieldObject);
        result.append(" </").append(fieldName).append(">\n");
    }

    @Override
    public void serializeCollection(String indentation, StringBuilder result, Object fieldObject, String fieldName) {
        result.append(indentation).append('\t').append("<").append(fieldName).append(">\n");
        if (fieldObject == null) {
            result.append("null,\n");
        }
        Collection collection = (Collection) fieldObject;
        int i = 0;
        for (Object elem : collection) {
            result.append(indentation).append('\t').append('\t').append("<").append(i).append("> ");
            result.append(elem).append("</").append(i).append("> ").append("\n");
            i += 1;
        }
        result.append(indentation).append("\t").append(" </").append(fieldName).append(">\n");
    }

    @Override
    public String getStart(String className) {
        return "<" + className + ">\n";
    }

    @Override
    public String getEnd(String className) {
        return "</" + className + ">";
    }
}

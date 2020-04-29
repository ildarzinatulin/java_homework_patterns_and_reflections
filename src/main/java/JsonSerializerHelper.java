import java.util.Collection;

class JsonSerializerHelper implements SerializerHelper{
    public void serializeClass(String indentation, StringBuilder result, Object fieldObject,
                               ClassSerializer classSerializer, String fieldName) {
        result.append(indentation).append('\t').append("\"").append(fieldName).append("\"").append(" : ");
        result.append(classSerializer.getFieldsWithIndentation(fieldObject, indentation + '\t'));
    }

    public void serializePrimitiveTypeOrWrapperOrString(String indentation, StringBuilder result, Object fieldObject,
                                                        String fieldName) {
        result.append(indentation).append('\t').append("\"").append(fieldName).append("\"").append(" : ");
        result.append("\"").append(fieldObject).append("\"").append(",\n");
    }

    public void serializeCollection(String indentation, StringBuilder result, Object fieldObject, String fieldName) {
        result.append(indentation).append('\t').append("\"").append(fieldName).append("\"").append(" : ");
        if (fieldObject == null) {
            result.append("null,\n");
        }
        result.append("[\n");
        Collection collection = (Collection) fieldObject;
        for (Object elem : collection) {
            result.append(indentation).append('\t').append('\t').append("\"").append(elem).append("\"").append(",\n");
        }
        result.append(indentation).append('\t').append("],\n");
    }

    public String getStart(String className) {
        return "{\n";
    }
    public String getEnd(String className) {
        return "},\n";
    }
}

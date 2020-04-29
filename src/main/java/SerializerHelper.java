public interface SerializerHelper {
    void serializeClass(String indentation, StringBuilder result, Object fieldObject, ClassSerializer classSerializer,
                        String fieldName);
    void serializePrimitiveTypeOrWrapperOrString(String indentation, StringBuilder result, Object fieldObject,
                                                 String fieldName);
    void serializeCollection(String indentation, StringBuilder result, Object fieldObject, String fieldName);
    String getStart(String className);
    String getEnd(String className);
}


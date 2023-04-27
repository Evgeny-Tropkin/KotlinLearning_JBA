fun containsKeyAndValue(map: Map<String, String>, value: String): Boolean {
    // put your code here
    return map.containsKey(value) and map.containsValue(value)
}
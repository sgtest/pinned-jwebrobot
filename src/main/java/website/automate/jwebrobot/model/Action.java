package website.automate.jwebrobot.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import website.automate.jwebrobot.exceptions.UnknownActionException;

public class Action {

    private ActionType type;
    
    private Map<String, CriteriaValue> criteriaValueMap = new HashMap<>();
    
    public CriteriaValue getCriteriaOrDefault(CriteriaType type){
        CriteriaValue value = getCriteria(CriteriaType.DEFAULT);
        if(value != null){
            return value;
        }
        return getCriteria(type);
    }
    
    public CriteriaValue getCriteria(CriteriaType type){
        return criteriaValueMap.get(type.getName());
    }
    
    public ActionType getType() {
        return type;
    }
    
    public void init(Map<String, Object> actionWrapper){
        Iterator<Entry<String, Object>> actionWrapperIterator = actionWrapper.entrySet().iterator();
        Entry<String, Object> action = actionWrapperIterator.next();
        this.type = findTypeByName(action.getKey());
        initCriteriaValueMap(action.getValue());
    }
    
    private ActionType findTypeByName(String actionName){
        ActionType type = ActionType.findByName(actionName);
        if(type == null){
            throw new UnknownActionException(actionName);
        }
        return type;
    }
    
    @SuppressWarnings("unchecked")
    private void initCriteriaValueMap(Object value){
        if(value instanceof Map){
            addAll((Map<String, Object>)value);
        } else {
            addDefault(value);
        }
    }
    
    private void addDefault(Object value){
        criteriaValueMap.put(CriteriaType.DEFAULT.getName(), new CriteriaValue(value));
    }
    
    private void addAll(Map<String, Object> values){
        for(Entry<String, Object> entry : values.entrySet()){
            criteriaValueMap.put(entry.getKey(), new CriteriaValue(entry.getValue()));
        }
    }
    
    public String getUrl(){
        return getCriteriaOrDefaultAsString(CriteriaType.URL);
    }
    
    public String getIf(){
        return getCriteriaAsString(CriteriaType.IF);
    }
    
    public String getUnless(){
        return getCriteriaAsString(CriteriaType.UNLESS);
    }
    
    public String getSelector(){
        return getCriteriaOrDefaultAsString(CriteriaType.SELECTOR);
    }
    
    public String getText(){
        return getCriteriaOrDefaultAsString(CriteriaType.TEXT);
    }
    
    public String getTime(){
        return getCriteriaOrDefaultAsString(CriteriaType.TIME);
    }
    
    public String getTimeout(){
        return getCriteriaOrDefaultAsString(CriteriaType.TIMEOUT);
    }

    public String getValue(){
        return getCriteriaOrDefaultAsString(CriteriaType.VALUE);
    }
    
    public Boolean getClear(){
        return getCriteriaOrDefault(CriteriaType.CLEAR).asBoolean();
    }
    
    public String getScenario(){
        return getCriteriaOrDefaultAsString(CriteriaType.SCENARIO);
    }
    
    private String getCriteriaAsString(CriteriaType type){
        CriteriaValue value = getCriteria(type);
        if(value != null){
            return value.asString();
        }
        return null;
    }
    
    private String getCriteriaOrDefaultAsString(CriteriaType type){
        return getCriteriaOrDefault(type).asString();
    }
    
    public void setUrl(String url){
        criteriaValueMap.put(CriteriaType.URL.getName(), new CriteriaValue(url));
    }
    
    public void setSelector(String selector){
        criteriaValueMap.put(CriteriaType.SELECTOR.getName(), new CriteriaValue(selector));
    }
    
    public void setType(ActionType type) {
        this.type = type;
    }

    public Map<String, CriteriaValue> getCriteriaValueMap() {
        return criteriaValueMap;
    }

    public void setCriteriaValueMap(Map<String, CriteriaValue> criteriaValueMap) {
        this.criteriaValueMap = criteriaValueMap;
    }
}
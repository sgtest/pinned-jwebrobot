package website.automate.jwebrobot.models.mapper;

import website.automate.jwebrobot.exceptions.TooManyActionsException;
import website.automate.jwebrobot.exceptions.UnknownActionException;
import website.automate.jwebrobot.models.mapper.actions.ActionMapper;
import website.automate.jwebrobot.models.mapper.actions.ActionMapperFactory;
import website.automate.jwebrobot.models.scenario.actions.Action;
import website.automate.jwebrobot.utils.CollectionMapper;

import javax.inject.Inject;
import java.util.Map;


public class StepsMapper extends CollectionMapper<Object, Action> {

    private final ActionMapperFactory actionMapperFactory;

    @Inject
    public StepsMapper(ActionMapperFactory actionMapperFactory) {
        this.actionMapperFactory = actionMapperFactory;
    }

    @Override
    public Action map(Object source) {
        Action action;

        Map<String, Object> actionsMap = (Map<String, Object>) source;

        if (actionsMap.keySet().size() != 1) {
            throw new TooManyActionsException(actionsMap.keySet().size());
        }

        String actionName = actionsMap.keySet().iterator().next();

        ActionMapper<Action> actionMapper = actionMapperFactory.getInstance(actionName);

        if (actionMapper == null) {
            throw new UnknownActionException(actionName);
        }

        Object actionMap = actionsMap.get(actionName);
        action = actionMapper.map(actionMap);

        return action;
    }


    @Override
    public void map(Object source, Action target) {
        // NOP
    }

}

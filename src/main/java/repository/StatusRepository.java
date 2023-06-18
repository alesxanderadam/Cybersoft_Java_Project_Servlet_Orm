package repository;

import model.StatusModel;
import model.UserModel;

import java.util.List;

public class StatusRepository extends UtilsRepository{
    public List<StatusModel> statuses = findAllModels("status", new String[]{"id", "name"}, StatusModel.class);

}

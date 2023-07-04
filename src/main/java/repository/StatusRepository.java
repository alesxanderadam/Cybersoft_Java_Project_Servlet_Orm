package repository;

import entity.StatusModel;

import java.util.List;

public class StatusRepository extends UtilsRepository{
    public List<StatusModel> statuses = findAllModels("status", new String[]{"id", "name"}, StatusModel.class);
}

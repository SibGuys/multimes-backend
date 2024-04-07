package org.multimes.backend.core.web.repository;

import org.multimes.backend.core.web.model.entities.Dialog;
import org.multimes.backend.core.web.repository.interfaces.IDialogRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DialogRepository implements IDialogRepository {

    private final JdbcTemplate jdbcTemplate;

    public DialogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int add(Dialog dialog) {
        String sql = "insert into inters (id_in_messenger, full_name, messenger_type) values (?, ?, ?)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                conn -> {
                    PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setLong(1, dialog.getIdInMessenger());
                    preparedStatement.setString(2, dialog.getFullName());
                    preparedStatement.setString(3, dialog.getMessengerType());
                    return preparedStatement;
                }, generatedKeyHolder);
        return (Integer) generatedKeyHolder.getKeys().get("inter_id");
    }

    @Override
    public Dialog getById(int id) {
        String sql = "select * from inters where inter_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Dialog.class), id);
    }

    @Override
    public int checkExistsWithIdInMessenger(long id) {
        String sql = "select * from inters where id_in_messenger = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id);
        List<Dialog> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            Dialog d = new Dialog(
                    (Integer) row.get("inter_id"),
                    (Long) row.get("id_in_messenger"),
                    (String) row.get("full_name"),
                    (String) row.get("messenger_type"));
            result.add(d);
        }
        if (!result.isEmpty()) {
            return result.get(0).getInterId();
        } else {
            return -1;
        }
    }

    @Override
    public List<Dialog> getAll() {
        String sql = "select * from inters";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<Dialog> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            Dialog d = new Dialog(
                    (Integer) row.get("inter_id"),
                    (Long) row.get("id_in_messenger"),
                    (String) row.get("full_name"),
                    (String) row.get("messenger_type"));
            result.add(d);
        }
        return result;
    }

}

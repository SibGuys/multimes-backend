package org.multimes.backend.core.web.repository;

import org.multimes.backend.core.web.model.Dialog;
import org.multimes.backend.core.web.repository.interfaces.IDialogRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public void add(Dialog dialog) {
        String sql = "insert into inters (id_in_messenger, messenger_type, full_name) values (?, ?, ?)";
        jdbcTemplate.update(sql,
                dialog.getId(), dialog.getMessenger(), dialog.getUsername());
    }

    @Override
    public Dialog getById(long id) {
        String sql = "select id_in_messenger, messenger_type, full_name from inters where inter_id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                new BeanPropertyRowMapper<>(Dialog.class),
                id);
    }

    @Override
    public boolean checkExistsWithIdInMessenger(long id) {
        String sql = "select id_in_messenger, messenger_type, full_name from inters where id_in_messenger = ?";
        List<Dialog> dialogs = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Dialog.class),
                id);
        return !dialogs.isEmpty();
    }

    @Override
    public List<Dialog> getAll() {
        String sql = "select id_in_messenger, full_name, messenger_type from inters";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<Dialog> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            Dialog dialog = new Dialog((Long) row.get("id_in_messenger"), (String) row.get("messenger_type"),
                    (String) row.get("full_name"));
            result.add(dialog);
        }
        return result;
    }
}

package org.multimes.backend.core.web.repository;

import org.multimes.backend.core.web.model.Message;
import org.multimes.backend.core.web.repository.interfaces.IMessageRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MessageRepository implements IMessageRepository {

    private final JdbcTemplate jdbcTemplate;

    public MessageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Message message) {
        String sql = "insert into messages (mes_text, is_inter, inter_id) values (?, ?, ?)";

        jdbcTemplate.update(sql,
                message.getText(), message.getIsInter(), message.getInterId());

    }

    @Override
    public Message getById(int id) {
        String sql = "select mes_text, is_inter, inter_id from messages where mes_id = ?";

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Message.class), id);
    }

    @Override
    public List<Message> getAll() {
        String sql = "select mes_text, is_inter, inter_id from messages";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<Message> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            Message message = new Message((String) row.get("mes_text"), (Boolean) row.get("is_inter"),
                    (Integer) row.get("inter_id"));
            result.add(message);
        }
        return result;
    }

}

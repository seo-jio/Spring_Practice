package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private Map<Long, Member> store = new HashMap<>(); // 동시성 이슈 때문에 실무에서는 ConcurrentHashMap 사용
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    private MemberRepository() {

    }

    public static MemberRepository getInstance() {
        return instance;
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public void clearStore() {
        store.clear();
    }
}

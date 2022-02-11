package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    //HashMap은 Map인터페이스의 구현 클래스이다.(null값 허용)
    //TreeMap은 Key값들에 대한 정렬이 이루어진다.
    //이를 사용하여 키-값 쌍 모음(중복x, 순서x)을 만들 수 있다.
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
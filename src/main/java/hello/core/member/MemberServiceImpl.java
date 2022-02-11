package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //기존 코드
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    //변경 코드 (인터페이스에만 의존할 수 있도록)
    //인터페이스의 구현 객체를 대신 생성하고 주입해줘야 한다. by AppConfig
    //여기서는 생성자를 통해 연결시켜줄 거임
    //final로 선언하면 기본 할당이든 생성자 할당이든 할당돼있어야 한다.
    private final MemberRepository memberRepository;

    //생성자
    //MemoryMemberRepository는 AppConfig에서 생성되어
    //MemberSerivceImpl 생성자의 매개변수를 통해 전달될 뿐이다. -> 생성자 주입
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

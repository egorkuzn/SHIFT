import ru.cft.clorental.domain.RoleEntity;
import ru.cft.clorental.domain.UserEntity;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    // 1. Сохраняем роли
    @Override
    public RoleEntity saveRole(RoleEntity role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    // 2. Сохраняем людей
    @Override
    public UserEntity saveUser(UserEntity user) {
        log.info("Saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    // 3. Добавлем людям роли
    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        UserEntity user = userRepo.findByUsername(username);
        RoleEntity role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }
}

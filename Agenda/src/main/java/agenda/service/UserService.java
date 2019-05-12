package agenda.service;

import agenda.repository.classes.RepositoryUserMock;

public class UserService {
    private RepositoryUserMock repositoryUserMock;

    public UserService() {
        this.repositoryUserMock = new RepositoryUserMock();
    }
}

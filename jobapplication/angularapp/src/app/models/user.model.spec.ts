import { User } from "./user.model";

describe('User Model', () => {

  fit('Frontend_should_create_an_instance_User_with_defined_properties', () => {
    // Create a sample User object
    const user: User = {
      userId: 1,
      email: 'testuser@example.com',
      password: 'securepassword123',
      username: 'testuser',
      mobileNumber: '1234567890',
      userRole: 'Admin'
    };

    expect(user).toBeTruthy();
    expect(user.userId).toBeDefined();
    expect(user.email).toBeDefined();
    expect(user.password).toBeDefined();
    expect(user.username).toBeDefined();
    expect(user.mobileNumber).toBeDefined();
    expect(user.userRole).toBeDefined();

    // Additional checks for values
    expect(user.userId).toBe(1);
    expect(user.email).toBe('testuser@example.com');
    expect(user.password).toBe('securepassword123');
    expect(user.username).toBe('testuser');
    expect(user.mobileNumber).toBe('1234567890');
    expect(user.userRole).toBe('Admin');
  });

});

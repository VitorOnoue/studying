export declare class Authentication {
    pwdToHash(password: string): Promise<string>;
    pwdCompare(storedPassword: string, suppliedPassword: string): Promise<boolean>;
}
export declare const authenticationService: Authentication;
//# sourceMappingURL=authentication.d.ts.map
import { scrypt, randomBytes } from 'crypto';
import { promisify } from 'util';
const scryptAsync = promisify(scrypt);
export class Authentication {
    async pwdToHash(password) {
        const salt = randomBytes(8).toString('hex');
        const buf = (await scryptAsync(password, salt, 64));
        return `${buf.toString('hex')}.${salt}`;
    }
    async pwdCompare(storedPassword, suppliedPassword) {
        const passwordParts = storedPassword.split('.');
        if (passwordParts.length != 2) {
            throw new Error('');
        }
        const [hashedPassword, salt] = passwordParts;
        const buf = (await scryptAsync(suppliedPassword, salt, 64));
        return buf.toString('hex') === hashedPassword;
    }
}
export const authenticationService = new Authentication;
//# sourceMappingURL=authentication.js.map
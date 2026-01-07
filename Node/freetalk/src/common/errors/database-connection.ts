import { CustomError } from "./custom-error.js";

export class DatabaseConnectionError extends CustomError {
    statusCode = 500;

    constructor() {
        super('database connection error!');
    }

    generateErrors() {
        return [{ message: 'database connection error!' }];
    }
}
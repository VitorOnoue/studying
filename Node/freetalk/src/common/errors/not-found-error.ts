import { CustomError } from "./custom-error.js";

export class NotFoundError extends CustomError {
    statusCode = 404;
    constructor(){
        super('not found!');
    }
    generateErrors() {
        return [{ message: 'not found!' }];
    }
}

// new NotFoundError();
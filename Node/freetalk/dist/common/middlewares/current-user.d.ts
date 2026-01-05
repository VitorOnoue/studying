import { Request, Response, NextFunction } from 'express';
declare global {
    interface JwtPayload {
        email: string;
        password: string;
    }
    namespace Express {
        interface Request {
            currentUser?: JwtPayload;
        }
    }
}
export declare const currentUser: (req: Request, res: Response, next: NextFunction) => void;
//# sourceMappingURL=current-user.d.ts.map
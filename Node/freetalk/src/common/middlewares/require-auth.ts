import { Request, Response, NextFunction } from 'express';

export const requireAuth = async (req: Request, res: Response, next: NextFunction) => {
    if (!req.currentUser) {
        const error = new Error('not authorized') as CustomError;
        error.status = 401;
        return next(error);
    }
    next();
};
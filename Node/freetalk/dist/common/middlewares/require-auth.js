export const requireAuth = async (req, res, next) => {
    if (!req.currentUser) {
        const error = new Error('not authorized');
        error.status = 401;
        return next(error);
    }
    next();
};
//# sourceMappingURL=require-auth.js.map
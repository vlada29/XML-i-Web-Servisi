"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const functions = require("firebase-functions");
const admin = require("firebase-admin");
const cors = require("cors");
admin.initializeApp();
const corsHandler = cors({ origin: true });
exports.addComment = functions.https.onRequest((request, response) => {
    corsHandler(request, response, () => {
        const komentar = request.body;
        const s = admin.database().ref('comments').push(komentar);
    });
});
exports.addRating = functions.https.onRequest((request, response) => {
    corsHandler(request, response, () => {
        const ocena = request.body;
        const s = admin.database().ref('ratings').push(ocena);
    });
});
exports.approveComment = functions.https.onRequest((request, response) => {
    corsHandler(request, response, () => {
        const komentar = request.body;
        const s = admin.database().ref('comments').child(komentar.cloudStorageKey).update({ odobren: true });
    });
});
exports.deleteComment = functions.https.onRequest((request, response) => {
    corsHandler(request, response, () => {
        const komentar = request.body;
        const s = admin.database().ref('comments').child(komentar.cloudStorageKey).remove();
        response.send('{}');
    });
});
//# sourceMappingURL=index.js.map
'use strict';

const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

exports.sendInvitationNotification = functions.database.ref('users/{user_id}/invitations/{invitation_id}').onWrite(event => {
    const user_id = event.params.user_id;
    const invitation_id = event.params.invitation_id;

    console.log("User ID: " + user_id + " |Invitation ID: " + invitation_id)
});
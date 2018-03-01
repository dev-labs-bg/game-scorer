'use strict';

const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

exports.sendInvitationNotification = functions.database.ref('users/{user_id}/invitations/{invitation_id}').onWrite(event => {
    const user_id = event.params.user_id;
    const invitation_id = event.params.invitation_id;
    
    return admin.database().ref('/users/' + user_id + '/invitations/' + invitation_id).once('value').then(queryResult => {
        const from_user_id = queryResult.val().fromUserId;
        const from_message = queryResult.val().message;

        console.log("From user: " + from_user_id);

        const from_data = admin.database().ref('users/' + from_user_id).once('value');
        const to_data = admin.database().ref('users/' + user_id).once('value');

        return Promise.all([from_data, to_data]).then(result => {
            
            const from_name = result[0].val().displayName;
            const to_name = result[1].val().displayName;
            const token_id = result[1].val().tokenId;

            const payload = {
                notification: {
                    title : "New Invitation From : " + from_name,
                    body : from_message,
                    icon : "default",
                    click_action: "bg.devlabs.gamescorer.TARGETNOTIFICATION"
                },
                data: {
                    message : from_message,
                    from_id : from_user_id
                }
            };

            return admin.messaging().sendToDevice(token_id, payload).then(result => {
                console.log("Notification Sent.");
            });
        });
    });
});
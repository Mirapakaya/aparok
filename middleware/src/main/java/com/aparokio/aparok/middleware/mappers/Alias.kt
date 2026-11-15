package com.aparokio.aparok.middleware.mappers

import aparok.Event.P2PStatus
import aparok.Event.Space
import com.aparokio.aparok.core_models.chats.Chat


typealias MEvent = aparok.Event
typealias MEventMessage = aparok.Event.Message
typealias MAccount = aparok.model.Account
typealias MAccountStatus = aparok.model.Account.Status
typealias MAccountStatusType = aparok.model.Account.StatusType
typealias MBlock = aparok.model.Block
typealias MObjectView = aparok.model.ObjectView
typealias MBText = aparok.model.Block.Content.Text
typealias MBTextStyle = aparok.model.Block.Content.Text.Style
typealias MBMarks = aparok.model.Block.Content.Text.Marks
typealias MBMark = aparok.model.Block.Content.Text.Mark
typealias MBMarkType = aparok.model.Block.Content.Text.Mark.Type
typealias MBFile = aparok.model.Block.Content.File
typealias MBFileState = aparok.model.Block.Content.File.State
typealias MBFileType = aparok.model.Block.Content.File.Type
typealias MBLink = aparok.model.Block.Content.Link
typealias MBLatex = aparok.model.Block.Content.Latex
typealias MBLatexProcessor = aparok.model.Block.Content.Latex.Processor
typealias MBLinkIconSize = aparok.model.Block.Content.Link.IconSize
typealias MBLinkCardStyle = aparok.model.Block.Content.Link.CardStyle
typealias MBLinkDescription = aparok.model.Block.Content.Link.Description
typealias MBLinkStyle = aparok.model.Block.Content.Link.Style
typealias MBBookmark = aparok.model.Block.Content.Bookmark
typealias MBookmarkState = aparok.model.Block.Content.Bookmark.State
typealias MBLayout = aparok.model.Block.Content.Layout
typealias MBLayoutStyle = aparok.model.Block.Content.Layout.Style
typealias MBDiv = aparok.model.Block.Content.Div
typealias MBDivStyle = aparok.model.Block.Content.Div.Style
typealias MBRelation = aparok.model.Block.Content.Relation
typealias MBAlign = aparok.model.Block.Align
typealias MBPosition = aparok.model.Block.Position
typealias MBSplitMode = aparok.Rpc.Block.Split.Request.Mode
typealias MBTableOfContents = aparok.model.Block.Content.TableOfContents

typealias MChatMessage = aparok.model.ChatMessage
typealias MChatState = aparok.model.ChatState
typealias MChatMessageContent = aparok.model.ChatMessage.MessageContent
typealias MChatMessageAttachment = aparok.model.ChatMessage.Attachment
typealias MChatMessageAttachmentType = aparok.model.ChatMessage.Attachment.AttachmentType
typealias MChatMessageReactions = aparok.model.ChatMessage.Reactions
typealias MChatMessageReactionIdentity = aparok.model.ChatMessage.Reactions.IdentityList
typealias MChatMessageBlock = aparok.model.ChatMessage.MessageBlock
typealias MChatMessageBlockText = aparok.model.ChatMessage.MessageBlockText
typealias MChatMessageBlockLink = aparok.model.ChatMessage.MessageBlockLink
typealias MChatMessageBlockLinkType = aparok.model.ChatMessage.MessageBlockLink.LinkType
typealias MChatMessageBlockEmbed = aparok.model.ChatMessage.MessageBlockEmbed

typealias MDV = aparok.model.Block.Content.Dataview
typealias MDVView = aparok.model.Block.Content.Dataview.View
typealias MDVViewType = aparok.model.Block.Content.Dataview.View.Type
typealias MDVViewCardSize = aparok.model.Block.Content.Dataview.View.Size
typealias MDVSort = aparok.model.Block.Content.Dataview.Sort
typealias MDVSortType = aparok.model.Block.Content.Dataview.Sort.Type
typealias MDVSortEmptyType = aparok.model.Block.Content.Dataview.Sort.EmptyType
typealias MDVFilter = aparok.model.Block.Content.Dataview.Filter
typealias MDVFilterCondition = aparok.model.Block.Content.Dataview.Filter.Condition
typealias MDVFilterQuickOption = aparok.model.Block.Content.Dataview.Filter.QuickOption
typealias MDVFilterOperator = aparok.model.Block.Content.Dataview.Filter.Operator
typealias MDVRelation = aparok.model.Block.Content.Dataview.Relation
typealias MDVDateFormat = aparok.model.Block.Content.Dataview.Relation.DateFormat
typealias MDVTimeFormat = aparok.model.Block.Content.Dataview.Relation.TimeFormat
typealias MDVFilterUpdate = aparok.Event.Block.Dataview.ViewUpdate.Filter
typealias MDVSortUpdate = aparok.Event.Block.Dataview.ViewUpdate.Sort
typealias MDVRelationUpdate = aparok.Event.Block.Dataview.ViewUpdate.Relation
typealias MDVViewFields = aparok.Event.Block.Dataview.ViewUpdate.Fields
typealias MDVObjectOrder = aparok.model.Block.Content.Dataview.ObjectOrder
typealias MDVGroupOrder = aparok.model.Block.Content.Dataview.GroupOrder
typealias MDVViewGroup = aparok.model.Block.Content.Dataview.ViewGroup
typealias MDVGroup = aparok.model.Block.Content.Dataview.Group
typealias MDVGroupStatus = aparok.model.Block.Content.Dataview.Status
typealias MDVGroupTag = aparok.model.Block.Content.Dataview.Tag
typealias MDVGroupCheckbox = aparok.model.Block.Content.Dataview.Checkbox

typealias MObjectType = aparok.model.ObjectType
typealias MSmartBlockType = aparok.model.SmartBlockType
typealias MOTypeLayout = aparok.model.ObjectType.Layout
typealias MRelationFormat = aparok.model.RelationFormat
typealias MRelationDataSource = aparok.model.Relation.DataSource
typealias MRelation = aparok.model.Relation
typealias MRelationLink = aparok.model.RelationLink
typealias MRelationOption = aparok.model.Relation.Option
typealias MObjectRestriction = aparok.model.Restrictions.ObjectRestriction
typealias MDVRestrictions = aparok.model.Restrictions.DataviewRestrictions
typealias MDVRestriction = aparok.model.Restrictions.DataviewRestriction

typealias MWidget = aparok.model.Block.Content.Widget
typealias MWidgetLayout = aparok.model.Block.Content.Widget.Layout
typealias MNetworkMode = aparok.Rpc.Account.NetworkMode

typealias MParticipantPermission = aparok.model.ParticipantPermissions

typealias MManifestInfo = aparok.model.ManifestInfo

typealias MProcess = aparok.Model.Process
typealias MProcessState = aparok.Model.Process.State
typealias MProcessProgress = aparok.Model.Process.Progress

typealias MNotification = aparok.model.Notification
typealias MNotificationActionType = aparok.model.Notification.ActionType
typealias MNotificationStatus = aparok.model.Notification.Status
typealias MImportErrorCode = aparok.model.Import.ErrorCode

typealias MMembership = aparok.model.Membership
typealias MMembershipStatus = aparok.model.Membership.Status
typealias MMembershipPaymentMethod = aparok.model.Membership.PaymentMethod
typealias MMembershipTierData = aparok.model.MembershipTierData
typealias MMembershipTierDataPeriodType = aparok.model.MembershipTierData.PeriodType
typealias MNameServiceNameType = aparok.model.NameserviceNameType
typealias MEmailVerificationStatus = aparok.model.Membership.EmailVerificationStatus
typealias MDetail = aparok.model.Detail

typealias MSpaceSyncStatus = aparok.Event.Space.Status
typealias MSpaceSyncNetwork = aparok.Event.Space.Network
typealias MSpaceSyncError = aparok.Event.Space.SyncError

typealias MP2PStatus = aparok.Event.P2PStatus.Status
typealias MP2PStatusUpdate = P2PStatus.Update
typealias MSyncStatusUpdate = Space.SyncStatus.Update

typealias MDeviceNetworkType = aparok.model.DeviceNetworkType

typealias MLinkPreview = aparok.model.LinkPreview

typealias MPublishState = aparok.Rpc.Publishing.PublishState
typealias MPublishStatus = aparok.Rpc.Publishing.PublishStatus

typealias MInviteType = aparok.model.InviteType
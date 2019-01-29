'use strict';

/**
 * Consumable.js controller
 *
 * @description: A set of functions called "actions" for managing `Consumable`.
 */

module.exports = {

  /**
   * Retrieve consumable records.
   *
   * @return {Object|Array}
   */

  find: async (ctx) => {
    if (ctx.query._q) {
      return strapi.services.consumable.search(ctx.query);
    } else {
      return strapi.services.consumable.fetchAll(ctx.query);
    }
  },

  /**
   * Retrieve a consumable record.
   *
   * @return {Object}
   */

  findOne: async (ctx) => {
    if (!ctx.params._id.match(/^[0-9a-fA-F]{24}$/)) {
      return ctx.notFound();
    }

    return strapi.services.consumable.fetch(ctx.params);
  },

  /**
   * Count consumable records.
   *
   * @return {Number}
   */

  count: async (ctx) => {
    return strapi.services.consumable.count(ctx.query);
  },

  /**
   * Create a/an consumable record.
   *
   * @return {Object}
   */

  create: async (ctx) => {
    return strapi.services.consumable.add(ctx.request.body);
  },

  /**
   * Update a/an consumable record.
   *
   * @return {Object}
   */

  update: async (ctx, next) => {
    return strapi.services.consumable.edit(ctx.params, ctx.request.body) ;
  },

  /**
   * Destroy a/an consumable record.
   *
   * @return {Object}
   */

  destroy: async (ctx, next) => {
    return strapi.services.consumable.remove(ctx.params);
  }
};
